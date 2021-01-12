import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConstants } from 'src/app/shared/core/app-constants';
import { HttpCore } from 'src/app/shared/core/http-core';

@Injectable({
  providedIn: 'root'
})
export class UsersService extends HttpCore{

  constructor(http: HttpClient) {
    super(http, AppConstants.USER_CONTROLLER);
   }

   public findByUserName(name: string): any {
      return this.get(null, name);
  }
}
