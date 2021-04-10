import { IOrder } from 'app/shared/model/order.model';
import { IDelivery } from 'app/shared/model/delivery.model';

export interface IClient {
  id?: number;
  iDclient?: number;
  name?: string;
  lastName?: string;
  address?: string;
  phoneNumber?: string;
  orders?: IOrder[];
  deliveries?: IDelivery[];
}

export class Client implements IClient {
  constructor(
    public id?: number,
    public iDclient?: number,
    public name?: string,
    public lastName?: string,
    public address?: string,
    public phoneNumber?: string,
    public orders?: IOrder[],
    public deliveries?: IDelivery[]
  ) {}
}
