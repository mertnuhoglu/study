// This will serve as a form of implicit documentation later on.
export type PrefName = "PET_NAME" | "SEATING_PREFERENCE" | "AGE";

// This will act as a "restricted dictionary".
type UserPref = Partial<Record<PrefName, any>>;

// ALMOST works (but we forgot one thing):
let prefs: UserPref = {
    "PET_NAME": "fido"
}