import {Request, Response} from "express";
import {AuthRepository} from "../repositories";

class AuthController {
  async login(req: Request, res: Response) {
    console.log("tried login")
    const {email, password} = req.body;
    const user = await AuthRepository.login({email, password})
    if (!user) {
      return res.status(400).json({error: true, message: "User not found"})
    }
    res.json(user);
  }
}

export default new AuthController()