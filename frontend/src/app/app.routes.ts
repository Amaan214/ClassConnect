import { Routes } from '@angular/router';
import { StudentComponent } from './student/student.component';
import { ClassroomComponent } from './classroom/classroom.component';
import { SubjectComponent } from './subject/subject.component';
import { TeacherComponent } from './teacher/teacher.component';
import { UpdateClassroomComponent } from './update-classroom/update-classroom.component';
import { UpdateStudentComponent } from './update-student/update-student.component';
import { UpdateSubjectComponent } from './update-subject/update-subject.component';
import { UpdateTeacherComponent } from './update-teacher/update-teacher.component';
import { AdminDashboardComponent } from './dashboard/admin-dashboard/admin-dashboard.component';
import { ClassroomDetailsComponent } from './classroom-details/classroom-details.component';

export const routes: Routes = [
    {path: 'classroom', component: ClassroomComponent},
    {path: 'student', component: StudentComponent},
    {path: 'subject', component: SubjectComponent},
    {path: 'teacher', component: TeacherComponent},
    {path: 'classroom/update/:id', component: UpdateClassroomComponent},
    {path: 'student/update/:id', component: UpdateStudentComponent},
    {path: 'subject/update/:id', component: UpdateSubjectComponent}, 
    {path: 'teacher/update/:id', component: UpdateTeacherComponent},
    {path: 'admin-dashboard', component: AdminDashboardComponent},
    {path: 'classroom/details/:id', component: ClassroomDetailsComponent},
    {path: '', redirectTo: '', pathMatch: 'full'}
    
];
