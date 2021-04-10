import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOrder, Order } from 'app/shared/model/order.model';
import { OrderService } from './order.service';
import { IClient } from 'app/shared/model/client.model';
import { ClientService } from 'app/entities/client/client.service';
import { IDelivery } from 'app/shared/model/delivery.model';
import { DeliveryService } from 'app/entities/delivery/delivery.service';

type SelectableEntity = IClient | IDelivery;

@Component({
  selector: 'jhi-order-update',
  templateUrl: './order-update.component.html',
})
export class OrderUpdateComponent implements OnInit {
  isSaving = false;
  clients: IClient[] = [];
  deliveries: IDelivery[] = [];
  dateDp: any;

  editForm = this.fb.group({
    id: [],
    iDorder: [null, [Validators.required]],
    orderNumber: [null, [Validators.required, Validators.max(100)]],
    content: [null, [Validators.required]],
    price: [null, [Validators.required]],
    date: [null, [Validators.required]],
    client: [],
    delivery: [],
  });

  constructor(
    protected orderService: OrderService,
    protected clientService: ClientService,
    protected deliveryService: DeliveryService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ order }) => {
      this.updateForm(order);

      this.clientService.query().subscribe((res: HttpResponse<IClient[]>) => (this.clients = res.body || []));

      this.deliveryService.query().subscribe((res: HttpResponse<IDelivery[]>) => (this.deliveries = res.body || []));
    });
  }

  updateForm(order: IOrder): void {
    this.editForm.patchValue({
      id: order.id,
      iDorder: order.iDorder,
      orderNumber: order.orderNumber,
      content: order.content,
      price: order.price,
      date: order.date,
      client: order.client,
      delivery: order.delivery,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const order = this.createFromForm();
    if (order.id !== undefined) {
      this.subscribeToSaveResponse(this.orderService.update(order));
    } else {
      this.subscribeToSaveResponse(this.orderService.create(order));
    }
  }

  private createFromForm(): IOrder {
    return {
      ...new Order(),
      id: this.editForm.get(['id'])!.value,
      iDorder: this.editForm.get(['iDorder'])!.value,
      orderNumber: this.editForm.get(['orderNumber'])!.value,
      content: this.editForm.get(['content'])!.value,
      price: this.editForm.get(['price'])!.value,
      date: this.editForm.get(['date'])!.value,
      client: this.editForm.get(['client'])!.value,
      delivery: this.editForm.get(['delivery'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrder>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
