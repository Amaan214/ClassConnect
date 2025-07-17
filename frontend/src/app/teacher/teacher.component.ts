import { Component, OnInit } from '@angular/core';
import { Teacher } from './teacher';
import { TeacherService } from './teacher.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-teacher',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './teacher.component.html',
  styleUrl: './teacher.component.css'
})
export class TeacherComponent implements OnInit {

  constructor(private tchServ: TeacherService,
    private router: Router) { }

  teacherList: Teacher[] = [];

  newTch: Teacher = {
    id: 0,
    name: '',
    employeeId: '',
    email: '',
    subjects: undefined
  };

  ngOnInit(): void {
    this.getAllTeacher();
  }

  getAllTeacher() {
    this.tchServ.getAllTeacher().subscribe((data) => {
      this.teacherList = data;
    });
  }

  getTeachById(sId: number) {
    this.tchServ.getTeacherById(sId).subscribe((res) => {
      this.teacherList.forEach((s, index) => {
        if (s.id == sId) this.teacherList[index] = res;
      });
    });
  }

  // --------------------------------------------------------------ADD METHOD----------------------------------------------------------------
  addTeacher() {
    const teachToAdd = {
      name: this.newTch.name,
      email: this.newTch.email,
      employeeId: this.newTch.employeeId
    }
    this.tchServ.addTeacher(teachToAdd).subscribe((response) => {
      console.log("Teacher added successfully.", response);
      this.teacherList.push(response);
      this.getAllTeacher();

      // this resets form
      this.newTch = {
        id: 0,
        name: '',
        employeeId: '',
        email: ''
      }
    });
  }

  // ----------------------------------------------------------------------UPDATE METHOD----------------------------------------------------------------
  updateTeacher(tId: number) {
    this.router.navigate(['teacher/update', tId]);
  }

  // ---------------------------------------------------------------------------DELETE METHOD--------------------------------------------------------------------
  delTeacher(id: number) {
    this.tchServ.deleteTeacher(id).subscribe({
      next: () => {
        console.log("Teacher deleted successfully");
        this.getAllTeacher();  // Re-fetch the updated list after deletion
      },
  
      error: (error) => {
        console.error('Id not found', error);
        // Optionally, re-add the classroom if deletion failed
        this.getAllTeacher();
      }
    });
  }

  confirmDelete(id: number) {
    const confirmed = confirm("Are you sure you want to delete this classroom?");
    if (confirmed) {
      this.delTeacher(id);
    }
  }

}
