import { Classroom } from "../classroom/classroom";
import { Teacher } from "../teacher/teacher";

export interface Subject {
    id?: number;
    name: string;
    code: string;
    teacher?: Teacher;
    classrooms?: Classroom[];
}
