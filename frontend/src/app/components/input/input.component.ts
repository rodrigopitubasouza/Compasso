import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.scss']
})
export class InputComponent implements OnInit {

  @Input()
  public type = 'text';

  @Input()
  public label!: string;

  @Input()
  public requiredLabel = false;

  @Input()
  public disabled = false;

  @Input()
  public readonly = false;

  @Input()
  public value: any;

  @Input()
  public max: number;

  @Input()
  public min: number;

  @Input()
  public tabindex = 0;

  @Input()
  public inputClass = '';

  @Input()
  public regex = false;

  @Input()
  public placeholder = '';

  constructor() { }

  ngOnInit(): void {
  }

  public ngModelChange(): void {
        this.value = this.value.replace(this.regex, '');
  }
}
