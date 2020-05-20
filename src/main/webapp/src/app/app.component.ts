import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Hello World Application';
  name: string = 'name';
  message = null;

  constructor(private http : HttpClient) {

  }

  sayHello() : void {
    this.http.get<HelloResponse>('hello/' + this.name)
      .subscribe(data => {
        this.message = data.message;
      });
  }
}

interface HelloResponse {
  message: string;
}
