import db from '../../database';

type User = {
  name: string;
  email: string;
  password: string;
};

class UserRepository {
  async create({ name, email, password }: User) {
    const [user] = await db.query(
      `
      INSERT INTO users (name, email, password)
      VALUES ($1, $2, $3)
      RETURNING *
    `,
      [name, email, password],
    );

    return user;
  }

  async findByEmail(email: string) {
    const [user] = await db.query(
      `
      SELECT * FROM users WHERE email = $1
    `,
      [email],
    );

    return user;
  }

  async update(id: string, { name, email, password }: User) {
    const [user] = await db.query(
      `
      UPDATE users SET name = $1, email = $2, password = $3
      WHERE id = $4
      RETURNING *
    `,
      [name, email, password, id],
    );

    return user;
  }
}
