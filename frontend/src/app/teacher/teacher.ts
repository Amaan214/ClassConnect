import { Subject } from "../subject/subject";

export interface Teacher {
    id?: number;
    name: string;
    email: string;
    employeeId: string;
    subjects?: Subject[];
}
