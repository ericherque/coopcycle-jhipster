import { Moment } from 'moment';
import { IClient } from 'app/shared/model/client.model';
import { IDelivery } from 'app/shared/model/delivery.model';
import { IRestaurant } from 'app/shared/model/restaurant.model';

export interface IOrder {
  id?: number;
  iDorder?: number;
  orderNumber?: number;
  content?: string;
  price?: number;
  date?: Moment;
  client?: IClient;
  delivery?: IDelivery;
  restaurants?: IRestaurant[];
}

export class Order implements IOrder {
  constructor(
    public id?: number,
    public iDorder?: number,
    public orderNumber?: number,
    public content?: string,
    public price?: number,
    public date?: Moment,
    public client?: IClient,
    public delivery?: IDelivery,
    public restaurants?: IRestaurant[]
  ) {}
}
