import {Component, OnInit, TemplateRef} from '@angular/core';
import {ClassDirectedService} from '../../../service/class-directed.service';
import {ClassDirected} from '../../../model/class-directed';
import {FormBuilder, FormGroup} from '@angular/forms';
import {UserService} from '../../../service/user.service';
import {User} from '../../../model/user';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {faSearch} from '@fortawesome/free-solid-svg-icons';
import {ClassSchedule} from '../../../model/class-schedule';

@Component({
  selector: 'app-assigned-class',
  templateUrl: './assigned-class.component.html',
  styleUrls: ['./assigned-class.component.css']
})
export class AssignedClassComponent implements OnInit {
  private assignedClass: ClassDirected;
  searchClientForm: FormGroup;
  clientFound: User[];
  classDirectedName: string;
  clientListOfClass: User[];
  faSearch = faSearch;
  schedule: ClassSchedule;

  constructor(private _classDirectedService: ClassDirectedService, private _fb: FormBuilder,
              private _userService: UserService, private _modalService: NgbModal) {
  }

  ngOnInit() {
    this._classDirectedService.getClassDirected().subscribe(classDirected => {
      this.setAssignedClass(classDirected);
    }, error => {
      console.log(error);
    });
    this.builderForm();

  }

  onSubmit() {
    const name = this.searchClientForm.controls.name.value;
    this._userService.getAllClientsLike(name).subscribe(clients => {
      this.clientFound = clients;
    }, error => {
      console.log(error);
    });
  }

  private builderForm() {
    this.searchClientForm = this._fb.group({
      name: ['']
    });
  }

  private setAssignedClass(classDirected: ClassDirected) {
    this.assignedClass = classDirected;
    this.classDirectedName = classDirected.typeClass.name;
    this.schedule = classDirected.classSchedule;
    this.clientListOfClass = classDirected.clientList;
  }

  addToClass(id: number) {
    this._classDirectedService.addClientToClass(id, this.assignedClass).subscribe(res => {
      if (res === true) {
        alert('Client added');
        const clientAdded = this.clientFound.find(user => user.id === id);
        this.clientListOfClass.push(clientAdded);
        this.clearForm();
      } else {
        alert('Client no added');
      }
    }, error => {
      alert(error.error.message);
    });
  }

  openModal(content: TemplateRef<any>) {
    this._modalService.open(content).result.then(() => {
      this.clearForm();
    }, () => {
      this.clearForm();
    });
  }

  private clearForm() {
    this.clientFound = [];
    this.searchClientForm.reset();

  }
}
