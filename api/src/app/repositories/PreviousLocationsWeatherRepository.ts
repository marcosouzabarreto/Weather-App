import db from '../../database';

type Weather = {
  humidity: number;
  temperature: number;
  latitude: number;
  longitude: number;
};

class PreviousLocationsWeatherRepository {
  async create(
    user_id: string,
    { humidity, temperature, latitude, longitude }: Weather,
  ) {
    const [location] = await db.query(
      `
      INSERT INTO previous_locations_weather (user_id, humidity, temperature, latitude, longitude)
      VALUES ($1, $2, $3, $4, $5)
      RETURNING *
    `,
      [user_id, humidity, temperature, latitude, longitude],
    );

    return location;
  }

  async findByAllByUserId(user_id: string) {
    const [allLocations] = await db.query(
      `
      SELECT * FROM previous_locations_weather WHERE user_id = $1
    `,
      [user_id],
    );

    return allLocations;
  }
}

export default new PreviousLocationsWeatherRepository();
