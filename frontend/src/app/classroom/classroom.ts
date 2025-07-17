import { Student } from "../student/student";
import { Subject } from "../subject/subject";

export interface Classroom {
    id?: number;
    section: string;
    grade: string;
    students?: Student[];
    subjects?: Subject[];
}
