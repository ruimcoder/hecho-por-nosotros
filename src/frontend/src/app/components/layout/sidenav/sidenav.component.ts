import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})
export class SidenavComponent implements OnInit {

  admin: Boolean = false;
  constructor() {
    this.getHomeByAuthorities();
  }

  ngOnInit(): void {
    this.getHomeByAuthorities()
  }


  getHomeByAuthorities() {
    let autorities = JSON.parse(localStorage.getItem('hxn-workload-user-claims') || '{}');

    if (autorities["ROLE_SUPER"]) {
      this.admin = true;
    
    
    }

    else
    {
      
    }
    
  

  }


}
