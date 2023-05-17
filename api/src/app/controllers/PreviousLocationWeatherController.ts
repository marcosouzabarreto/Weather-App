import { Request, Response } from 'express';
import { PreviousLocationsWeatherRepository } from '../repositories';

class PreviousLocationWeatherController {
  async show(req: Request, res: Response) {
    const { user_id } = req.params;
    const all_locations =
      await PreviousLocationsWeatherRepository.findByAllByUserId(user_id);
    res.json(all_locations);
  }

  async create(req: Request, res: Response) {
    const { user_id } = req.params;
    const { humidity, temperature, latitude, longitude } = req.body;

    const location = await PreviousLocationsWeatherRepository.create(user_id, {
      humidity,
      temperature,
      latitude,
      longitude,
    });
    return location;
  }
}

export default new PreviousLocationWeatherController();
