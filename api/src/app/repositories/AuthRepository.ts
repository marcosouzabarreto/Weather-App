import db from '../../database';

type LoginInfo = {
  email: string,
  password: string
};

class AuthRepository {
  async login(
    {email, password}: LoginInfo
  ) {
//    const [user] = await db.query(
//      `
//        SELECT *
//        FROM users
//        WHERE email = $1
//          AND password = $2
//      `,
//      [email, password],
//    );

    const user = {
      username: "Test",
      email: "test",
      password: "123"
    }

    return user;
  }

}

export default new AuthRepository();
