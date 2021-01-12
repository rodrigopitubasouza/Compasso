import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.scss']
})
export class ButtonComponent implements OnInit {

  @Input()
  public type!: string;

  @Input()
  public class!: string;

  @Input()
  public label!: string;

  @Input()
  public icon!: string;

  @Input()
  public tooltip!: string;

  @Input()
  public disabled = false;

  @Input()
  public loading = false;

  constructor() { }

  ngOnInit(): void {
  }

}
