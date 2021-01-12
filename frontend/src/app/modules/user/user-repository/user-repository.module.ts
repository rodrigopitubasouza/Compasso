import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { InputModule } from 'src/app/components/input/input.module';
import { ButtonModule } from 'src/app/components/button/button.module';
import { TableModule } from 'src/app/components/table/table.module';
import { UserRepositoryComponent} from './user-repository.component';
import { UserRepositoryRouting } from './user-repository.routing';

@NgModule({
  declarations: [UserRepositoryComponent],
  imports: [
    CommonModule,
    FormsModule,
    UserRepositoryRouting,
    InputModule,
    ButtonModule,
    TableModule,
  ]
})
export class UserRepositoryModule { }
