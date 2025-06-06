/* tslint:disable */
/* eslint-disable */
/* Code generated by ng-openapi-gen DO NOT EDIT. */

import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { HotelResponse } from '../../models/hotel-response';

export interface GetAllHotels$Params {
}

export function getAllHotels(http: HttpClient, rootUrl: string, params?: GetAllHotels$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<HotelResponse>>> {
  const rb = new RequestBuilder(rootUrl, getAllHotels.PATH, 'get');
  if (params) {
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<HotelResponse>>;
    })
  );
}

getAllHotels.PATH = '/hotels/';
