import { create } from 'zustand';

interface PageStore {
    whatPage: string,
    setWhatPage: (value: string) => void;
}
const usePageStore = create<PageStore>((set) => ({
    whatPage: 'login',
    setWhatPage: (value:string) => set({whatPage: value}),
}));

export default usePageStore;