import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { ClassroomComponent } from "./classroom/classroom.component";
import { StudentComponent } from "./student/student.component";
import { SubjectComponent } from "./subject/subject.component";
import { TeacherComponent } from "./teacher/teacher.component";
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet,
    ClassroomComponent,
    StudentComponent,
    SubjectComponent,
    TeacherComponent,
    RouterOutlet,
    RouterLink,
    RouterLinkActive,
    FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'ClassConnect';
  sidebarOpen = true;
}
