import { Classroom } from "../classroom/classroom";

export interface Student {
    id?: number;
    name: string;
    email: string;
    rollNumber: string;
    classroom?: Classroom;
}
