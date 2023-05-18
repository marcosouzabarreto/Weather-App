import express, { Express } from 'express';
import dotenv from 'dotenv';
import {
  user_routes,
  previous_locations_routes,
  default_routes,
} from './routes';

dotenv.config();

const app: Express = express();
const port = process.env.API_PORT;

app.use(express.json());

app.use(default_routes);
app.use('/user', user_routes);
app.use('/previous-locations', previous_locations_routes);

app.listen(port, () => {
  console.log(`🔥 Server is running at http://localhost:${port}`);
});
