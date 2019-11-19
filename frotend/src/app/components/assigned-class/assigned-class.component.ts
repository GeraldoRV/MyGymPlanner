import {Component, OnInit} from '@angular/core';
import {ClassDirectedService} from '../../service/class-directed.service';
import {ClassDirected} from '../../model/class-directed';
import {FormBuilder, FormGroup} from '@angular/forms';
import {UserService} from '../../service/user.service';
import {User} from '../../model/user';

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

  constructor(private _classDirectedService: ClassDirectedService, private _fb: FormBuilder,
              private _userService: UserService) {
  }

  ngOnInit() {
    this._classDirectedService.getClassDirected().subscribe(classDirected => {
      this.assignedClass = classDirected;
      this.classDirectedName = classDirected.typeClass.name;
      this.clientListOfClass = classDirected.clientList;
    }, error => {
      console.log(error);
    });
    this.searchClientForm = this._fb.group({
      name: ['']
    });
  }

  onSubmit() {
    const name = this.searchClientForm.controls.name.value;
    this._userService.getAllClientsLike(name).subscribe(clients => {
      this.clientFound = clients;
      console.log(clients);
    }, error => {
      console.log(error);
    });
  }

  addToClass(id: number) {
    this._classDirectedService.addClientToClass(id, this.assignedClass).subscribe(res => {
      if (res === true) {
        alert('Client added');
        const clientAdded = this.clientFound.find(user => user.id === id);
        this.clientListOfClass.push(clientAdded);
      } else {
        alert('Client no added');
      }
    }, error => {
      console.log(error);
    });
  }
}
