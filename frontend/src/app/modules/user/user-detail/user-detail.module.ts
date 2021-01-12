import { NgModule } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { InputModule } from 'src/app/components/input/input.module';
import { ButtonModule } from 'src/app/components/button/button.module';
import { TableModule } from 'src/app/components/table/table.module';
import { UserDetailComponent } from './user-detail.component';
import { UserDetailRouting } from './user-detail.routing';

@NgModule({
  declarations: [UserDetailComponent],
  imports: [
    CommonModule,
    FormsModule,
    UserDetailRouting,
    InputModule,
    ButtonModule,
    TableModule
  ],
  providers:
  [DatePipe]
})
export class UserDetailModule { }
