import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'

})
export class AppComponent {
  title = 'fullstack-frontend';

  handleClick() {
    const apiUrl = "http://localhost:8080";
    if (!apiUrl) {
      throw new Error('API_URL is not defined');
    }
    fetch(apiUrl + "/getnull")
      .then(response => response.json())
      .then(data => {
        console.log(data);
        // handle the data from the backend here
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });
  }
}
