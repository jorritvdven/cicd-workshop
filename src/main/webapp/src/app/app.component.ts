import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {HttpClient} from "@angular/common/http";
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Hello World Application';
  name: string = 'name';
  message: String|null = null;

  constructor(private http : HttpClient) { }

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
