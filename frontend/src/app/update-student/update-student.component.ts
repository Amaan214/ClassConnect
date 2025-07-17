import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Student } from '../student/student';
import { FormsModule } from '@angular/forms';
import { StudentService } from '../student/student.service';

@Component({
  selector: 'app-update-student',
  imports: [FormsModule],
  templateUrl: './update-student.component.html',
  styleUrl: './update-student.component.css'
})
export class UpdateStudentComponent {

  constructor(private stdService: StudentService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  id!: number;
  editStd: Student = {
    name: '',
    email: '',
    rollNumber: ''
  };

  ngOnInit() : void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));

    this.stdService.getStudentById(this.id).subscribe({
      next: (data) => {
        this.editStd = data;
      },
      error: (err) => {
        console.error('Failed to load student', err);
      }
    });
  }

  updateStudent() {
    this.stdService.updateStudent(this.id, this.editStd).subscribe({
      next: () => {
        console.log('Student udpated successfully');
        this.router.navigate(['/student']); // redirect to list page
      },
      });
    }
}

