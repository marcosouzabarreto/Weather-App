import db from '../../database';

type User = {
  username: string;
  email: string;
  password: string;
};

class UserRepository {
  async create({ username, email, password }: User) {
    const [user] = await db.query(
      `
      INSERT INTO users (username, email, password)
      VALUES ($1, $2, $3)
      RETURNING *
    `,
      [username, email, password],
    );

    return user;
  }

  async findAll() {
    const users = await db.query('SELECT * FROM users ORDER BY username ASC');

    return users;
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

  async delete(id: string) {
    await db.query(
      `
      DELETE FROM users WHERE id = $1
    `,
      [id],
    );
  }

  async findById(id: string) {
    const [user] = await db.query(
      `
      SELECT * FROM users WHERE id = $1
    `,
      [id],
    );

    return user;
  }

  async update(id: string, { username, email, password }: User) {
    const [user] = await db.query(
      `
      UPDATE users SET username = $1, email = $2, password = $3
      WHERE id = $4
      RETURNING *
    `,
      [username, email, password, id],
    );

    return user;
  }
}

export default new UserRepository();
