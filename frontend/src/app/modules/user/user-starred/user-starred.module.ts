import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { InputModule } from 'src/app/components/input/input.module';
import { ButtonModule } from 'src/app/components/button/button.module';
import { TableModule } from 'src/app/components/table/table.module';
import { UserStarredComponent} from './user-starred.component';
import { UserStarredRouting } from './user-starred.routing';

@NgModule({
  declarations: [UserStarredComponent],
  imports: [
    CommonModule,
    FormsModule,
    UserStarredRouting,
    InputModule,
    ButtonModule,
    TableModule,
  ]
})
export class UserStarredModule { }
