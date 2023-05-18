import { Router } from 'express';
import { UserController } from '../app/controllers';

const router = Router();

router.get('/', UserController.index);
router.get('/:id', UserController.show);
router.put('/:id', UserController.update);
router.post('/', UserController.create);
router.delete('/:id', UserController.delete);

export default router;
