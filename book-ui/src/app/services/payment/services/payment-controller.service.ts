/* tslint:disable */
/* eslint-disable */
/* Code generated by ng-openapi-gen DO NOT EDIT. */

import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { createPayment } from '../fn/payment-controller/create-payment';
import { CreatePayment$Params } from '../fn/payment-controller/create-payment';
import { delete$ } from '../fn/payment-controller/delete';
import { Delete$Params } from '../fn/payment-controller/delete';
import { PaymentResponse } from '../models/payment-response';

@Injectable({ providedIn: 'root' })
export class PaymentControllerService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `createPayment()` */
  static readonly CreatePaymentPath = '/payment/create';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `createPayment()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  createPayment$Response(params: CreatePayment$Params, context?: HttpContext): Observable<StrictHttpResponse<PaymentResponse>> {
    return createPayment(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `createPayment$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  createPayment(params: CreatePayment$Params, context?: HttpContext): Observable<PaymentResponse> {
    return this.createPayment$Response(params, context).pipe(
      map((r: StrictHttpResponse<PaymentResponse>): PaymentResponse => r.body)
    );
  }

  /** Path part for operation `delete()` */
  static readonly DeletePath = '/payment/delete/{id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `delete()` instead.
   *
   * This method doesn't expect any request body.
   */
  delete$Response(params: Delete$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return delete$(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `delete$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  delete(params: Delete$Params, context?: HttpContext): Observable<void> {
    return this.delete$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

}
