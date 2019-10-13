import { Component, OnInit, Input } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";

// In your App's module:

@Component({
  selector: 'employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit{

  @Input() employees: Employee[];
  carFound:String;

  constructor(private httpClient: HttpClient){}

  ngOnInit(){
    console.log(this.employees);
  }


  deleteEmployee(id:Number):Observable<any>{
    console.log("HIHIHI");
    return this.httpClient.delete("api" + "/"+ id);

  }


  deleteMethod(id:Number){
    console.log("Delete Request");
    this.deleteEmployee(id).subscribe(() => {
      location.reload();
      console.log("user deleted");
    }
  );
  }


  findMethod(id:Number){
    this.carFound = "Your car is parked at pillar #" +id +".";
      console.log("car found ");
  }


  public registerEmployee(employee:Employee): Observable<Page> {
    console.log("Car Register Request Received")
    const headers = new HttpHeaders({'Content-Type':'application/json; charset=utf-8'});

    return this.httpClient.post<Page>('/api/employee',JSON.stringify(employee),{headers:headers});
  }

}
