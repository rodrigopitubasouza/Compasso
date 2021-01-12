import { NgModule } from '@angular/core';
import { UserRouting } from './user.routing';
import { TesteComponent } from './teste/teste.component';

@NgModule({
  declarations: [TesteComponent],
  imports: [
    UserRouting,
  ]
})
export class UserModule { }
