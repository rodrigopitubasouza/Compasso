
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { NotFoundComponent } from './components/not-found/not-found.component';

@NgModule({
  declarations: [NavbarComponent, FooterComponent, NotFoundComponent],
  imports: [
    CommonModule,
    RouterModule,
    HttpClientModule
  ],
  exports: [
    NavbarComponent,
    FooterComponent,
    NotFoundComponent
  ],
})
export class SharedModule { }
