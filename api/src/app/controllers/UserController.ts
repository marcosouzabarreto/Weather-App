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
    const { name, email, password } = req.body;

    if (!name) return res.status(400).json({ error: 'Name not provided' });

    const userAlreadyExists = await UserRepository.findByEmail(email);
    if (userAlreadyExists)
      return res.status(400).json({ error: 'User already exists' });

    const user = await UserRepository.create({ name, email, password });
    return user;
  }

  async update(req: Request, res: Response) {
    const { id } = req.params;
    const { name, email, password } = req.body;

    if (!name) return res.status(400).json({ error: 'Name not provided' });

    const contactByEmail = await UserRepository.findByEmail(email);
    if (contactByEmail && contactByEmail.id !== id)
      return res.status(400).json({ error: 'Email already exists' });

    const user = await UserRepository.update(id, { name, email, password });
    return user;
  }
}

export default new UserController();
