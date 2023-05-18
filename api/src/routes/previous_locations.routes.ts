import { Router } from 'express';
import { PreviousLocationWeatherController } from '../app/controllers';

const router = Router();

router.get('/', (req, res) => {
  res.send('PreviousLocationRoutes');
});
router.post('/:id', PreviousLocationWeatherController.show);

export default router;
