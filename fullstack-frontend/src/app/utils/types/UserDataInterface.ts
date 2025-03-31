export enum Status {
    Active = `Active`,
    Inactive = `Inactive` 
}

export enum Role {
    Student = `Student`, 
    Teacher = `Teacher`, 
    Admin = `Admin`
}

export interface UserData {
    id: number,
    name: string,
    surname: string,
    email: string,
    createdAt: string,
    status: Status,
    role: Role
}
    