import {User} from "@/types/User";

export interface AuthState {
    isLoggedIn: boolean;
    user: User | null;
    lastUpdated: number;
}