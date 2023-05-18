import { UserRepository } from '../repositories';
import { Request, Response } from 'express';

class UserController {
  async index(req: Request, res: Response) {
    const allUsers = await UserRepository.findAll();
    res.json(allUsers);
  }

  async show(req: Request, res: Response) {
    const { id } = req.params;
    const user = await UserRepository.findById(id);
    res.json(user);
  }

  async create(req: Request, res: Response) {
    const { username, email, password } = req.body;

    if (!username)
      return res.status(400).json({ error: 'Username not provided' });

    const userAlreadyExists = await UserRepository.findByEmail(email);
    if (userAlreadyExists)
      return res.status(400).json({ error: 'User already exists' });

    const user = await UserRepository.create({ username, email, password });
    console.log('CRIOU', user);
    res.json(user);
  }

  async update(req: Request, res: Response) {
    const { id } = req.params;
    const { username, email, password } = req.body;

    if (!username)
      return res.status(400).json({ error: 'Username not provided' });

    const contactByEmail = await UserRepository.findByEmail(email);
    if (contactByEmail && contactByEmail.id !== id)
      return res.status(400).json({ error: 'Email already exists' });

    const user = await UserRepository.update(id, { username, email, password });
    res.json(user);
  }

  async delete(req: Request, res: Response) {
    const { id } = req.params;
    await UserRepository.delete(id);
    res.sendStatus(200);
  }
}

export default new UserController();
