import chai from 'chai';
import chaiHttp from 'chai-http';
import app from '../';
import { expect } from 'chai';

chai.use(chaiHttp);

describe('UserController', () => {
  let userId: string;

  it('should create a new user', async () => {
    const res = await chai.request(app).post('/users').send({
      username: 'Test User',
      email: 'testuser@example.com',
      password: 'password',
    });

    expect(res).to.have.status(200);
    expect(res.body).to.have.property('id');
    userId = res.body.id;
  });

  it('should get all users', async () => {
    const res = await chai.request(app).get('/users');
    expect(res).to.have.status(200);
    expect(res.body).to.be.an('array');
  });

  it('should get a user by id', async () => {
    const res = await chai.request(app).get(`/users/${userId}`);
    expect(res).to.have.status(200);
    expect(res.body).to.have.property('id', userId);
  });

  it('should update a user', async () => {
    const res = await chai.request(app).put(`/users/${userId}`).send({
      username: 'Updated User',
      email: 'updateduser@example.com',
      password: 'newpassword',
    });

    expect(res).to.have.status(200);
    expect(res.body).to.have.property('username', 'Updated User');
  });

  it('should delete a user', async () => {
    const res = await chai.request(app).delete(`/users/${userId}`);
    expect(res).to.have.status(200);
  });

  // after(async () => {
  // Clear db
  // });
});
