import { Component ,OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {EmployeeService} from "./services/employee.service";
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'app';

  page: Page;
  loading: boolean;

  constructor(private employeeService : EmployeeService){}

  ngOnInit(){
    this.loading = true;
    this.loadData();
    this.employeeService.reloadMainList().subscribe(event=>{
      this.loadData();
    });
  }

  loadData(){
    this.employeeService.loadAllEmployee() .subscribe(data => {
      this.page = data;
      this.loading = false;
    });
  }

}
