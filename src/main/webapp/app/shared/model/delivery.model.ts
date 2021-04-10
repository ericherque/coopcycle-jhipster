import { IOrder } from 'app/shared/model/order.model';
import { IClient } from 'app/shared/model/client.model';
import { IRestaurant } from 'app/shared/model/restaurant.model';

export interface IDelivery {
  id?: number;
  iDdelivery?: number;
  name?: string;
  lastName?: string;
  position?: string;
  orders?: IOrder[];
  client?: IClient;
  restaurants?: IRestaurant[];
}

export class Delivery implements IDelivery {
  constructor(
    public id?: number,
    public iDdelivery?: number,
    public name?: string,
    public lastName?: string,
    public position?: string,
    public orders?: IOrder[],
    public client?: IClient,
    public restaurants?: IRestaurant[]
  ) {}
}
