import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-subscriptions-component',
  templateUrl: './subscriptions.component.html',
  styleUrls: ['./subscriptions.component.css']
})
export class SubscriptionsComponentComponent implements OnInit {

  constructor(private _activRoute: ActivatedRoute) {

  }

  ngOnInit(): void {

  }
}
