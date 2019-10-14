import {Component} from '@angular/core';
import {NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {EmployeeService} from "../services/employee.service";

@Component({
  selector: 'employee-register',
  templateUrl: './employee-register.component.html',
  styleUrls: ['./employee-register.component.css']
})

export class EmployeeRegisterComponent   {
  employeeForm: FormGroup;
  model: NgbModalRef;
  saving: boolean;
  date: String = this.getNowDate();

  constructor(private modalService: NgbModal,private fb: FormBuilder,private employeeService : EmployeeService) {

    this.employeeForm = fb.group({
      'firstName':['',Validators.compose([Validators.required])],
      'lastName': ['',Validators.compose([Validators.required])],
      'gender': ['',Validators.compose([Validators.required])],
      'type': ['',Validators.compose([Validators.required])],
      'size': ['',Validators.compose([Validators.required])],
      'licensePlate': ['',Validators.compose([Validators.pattern('^[A-Z]{2}[ -][0-9]{1,2}(?: [A-Z])?(?: [A-Z]*)? [0-9]{4}$')])],
      'company': ['',Validators.compose([Validators.required])],
      'forHandicap': ['false',Validators.compose([])],
      'parkingDate': ['',Validators.compose([ Validators.required,
                                                        Validators.pattern('^((0[1-9]|[12]\\d|3[01])\\/(0[1-9]|1[0-2])\\/[12]\\d{3})$'),
                                                        this.dateValidator
        ])],
    });

  }

  get firstName() { return this.employeeForm.get('firstName'); }
  get lastName() { return this.employeeForm.get('lastName'); }
  get gender() { return this.employeeForm.get('gender'); }
  get company() { return this.employeeForm.get('company'); }
  get parkingDate() { return this.employeeForm.get('parkingDate'); }
  get size() { return this.employeeForm.get('size'); }
  get type() { return this.employeeForm.get('type'); }
  get licensePlate() { return this.employeeForm.get('licensePlate'); }
  get forHandicap() { return this.employeeForm.get('forHandicap'); }

  open(content) {
    this.model = this.modalService.open(content, {ariaLabelledBy: 'register-employee'});
  }

  onCancel(): void {
    this.resetForm();
  }

  onSubmit(employee: Employee): void {
    this.saving = true;
    this.employeeService.registerEmployee(employee).subscribe(value => {
      this.resetForm();
      this.employeeService.reloadMainList().emit(value);

    })
  }

  resetForm():void{
    this.saving = false;
    this.employeeForm.reset();
    this.model.close();
    this.model = null;
    this.date=this.getNowDate();
  }

  public dateValidator(control: FormControl): { [s: string]: boolean } {

    if( control.value == null){
      return {invalidDate: true};
    }

    let dateParts = control.value.split("/");
    if(dateParts.length != 3){
      return {invalidDate: true};
    }

    let date = new Date(parseInt(dateParts[2]), parseInt(dateParts[1]) - 1, parseInt(dateParts[0]));
    if (!( date.getTime() <= new Date().getTime())) {
      return {invalidDate: true};
    }
  }

  getNowDate() {
    var returnDate = "";
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth() + 1; //because January is 0!
    var yyyy = today.getFullYear();
    if (dd < 10) {
      returnDate += `0${dd}/`;
    } else {
      returnDate += `${dd}/`;
    }

    if (mm < 10) {
      returnDate += `0${mm}/`;
    } else {
      returnDate += `${mm}/`;
    }
    returnDate += yyyy;
    return returnDate;
  }

}
