import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpCore } from 'src/app/shared/core/http-core';
import { AppConstants } from 'src/app/shared/core/app-constants';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
  })
  export class UserRepositoryService extends HttpCore{

    constructor(http: HttpClient) {
      super(http, AppConstants.USER_CONTROLLER);
     }

     public findByUserName(userName: string): Observable<any> {
        return this.get(null, userName.concat(AppConstants.REPOSITORY_CONTROLLER));
    }
}
