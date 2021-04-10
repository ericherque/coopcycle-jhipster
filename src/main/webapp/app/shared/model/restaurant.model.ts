import { IDelivery } from 'app/shared/model/delivery.model';
import { IOrder } from 'app/shared/model/order.model';

export interface IRestaurant {
  id?: number;
  iDRestaurant?: number;
  name?: string;
  position?: string;
  rating?: number;
  deliveries?: IDelivery[];
  orders?: IOrder[];
}

export class Restaurant implements IRestaurant {
  constructor(
    public id?: number,
    public iDRestaurant?: number,
    public name?: string,
    public position?: string,
    public rating?: number,
    public deliveries?: IDelivery[],
    public orders?: IOrder[]
  ) {}
}
