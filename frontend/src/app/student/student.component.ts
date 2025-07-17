import { Component, OnInit } from '@angular/core';
import { Student } from './student';
import { StudentService } from './student.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-student',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit{

  constructor(private stdService: StudentService,
    private router: Router,
  ){}

stdList : Student[] = [];
newStd: Student = {
  id: 0,
  name:'',
  email:'',
  rollNumber:'',
  classroom: undefined
};

ngOnInit(): void {
    this.getAllStudents();
}

getAllStudents(){
  this.stdService.getAllStudent().subscribe((res) => {
    this.stdList = res;
  });
}

getStdById(sId: number){
  this.stdService.getStudentById(sId).subscribe((res) => {
    this.stdList.forEach((s, index) =>{
      if(s.id == sId) this.stdList[index] = res;
    });
  });
}

// --------------------------------------------------------------ADD METHOD----------------------------------------------------------------
addStudent(){
const studentToAdd = {
  name: this.newStd.name,
  email: this.newStd.email,
  rollNumber: this.newStd.rollNumber
}

  this.stdService.addStudent(studentToAdd).subscribe((response) => {
    console.log("Student added successfully.", response);
    this.stdList.push(response);
    this.getAllStudents();

    // this resets the form
    this.newStd = {
      id: 0,
      name: '',
      email: '',
      rollNumber: ''
    }
  });
}

  // ----------------------------------------------------------------------UPDATE METHOD----------------------------------------------------------------
updateStudent(sId: number){
  this.router.navigate(['student/update', sId])
}

  // ---------------------------------------------------------------------------DELETE METHOD--------------------------------------------------------------------
delStudent(id: number){
  this.stdService.deleteStudent(id).subscribe({
    next: () => {
      console.log("Student deleted successfully");
      this.getAllStudents()  // Re-fetch the updated list after deletion
    },

    error: (error) => {
      console.error('Id not found', error);
      // Optionally, re-add the classroom if deletion failed
      this.getAllStudents();
    }
  });
}

confirmDelete(id: number) {
  const confirmed = confirm("Are you sure you want to delete this classroom?");
  if (confirmed) {
    this.delStudent(id);
  }
}
}
