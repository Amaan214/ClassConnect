import { Component } from '@angular/core';
import { TeacherService } from '../teacher/teacher.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Teacher } from '../teacher/teacher';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-update-teacher',
  imports: [FormsModule],
  templateUrl: './update-teacher.component.html',
  styleUrl: './update-teacher.component.css'
})
export class UpdateTeacherComponent {
  constructor(private tchServ: TeacherService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  id!: number
  editTeach: Teacher = {
    name: '',
    email: '',
    employeeId: ''
  };

  ngOnInit() : void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));

    this.tchServ.getTeacherById(this.id).subscribe({
      next: (data) => {
        this.editTeach = data;
      },
      error: (err) => {
        console.error('Failed to load teacher', err);
      }
    });
  }

  updateTeacher() {
    this.tchServ.updateTeacher(this.id, this.editTeach).subscribe({
      next: () => {
        console.log('Teacher udpated successfully');
        this.router.navigate(['/teacher']); // redirect to list page
      },
      });
    }
}
