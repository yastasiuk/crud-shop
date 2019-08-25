import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent implements OnInit {
    search: string;

    constructor(private router: Router) { }

    ngOnInit() { }

    onSearchChange(newValue: string) {
        this.search = newValue;
    }

    onSearch(e: Event) {
        e.preventDefault();
        if (this.search) {
            const queryParams = {
                name: this.search
            };
            console.log('queryParams', queryParams);
            this.router.navigate(['/list'], { queryParams });
        }
    }

}
