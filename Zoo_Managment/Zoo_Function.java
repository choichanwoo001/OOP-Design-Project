package Zoo_Management;

import java.util.ArrayList;

public class Zoo_Function{
    // 기능 1 동물 등록/삭제/조회
    public void addAnimal(ArrayList<Animal> animals, Animal animal){
        animals.add(animal);
        System.out.println("success");
    }
    public void removeAnimal(ArrayList<Animal> animals, Animal animal){
        for(Animal ani : animals){
            if(ani.name.equals(animal.name))
                animals.remove(ani);
        }
    }

    public void addManeger(ArrayList<Maneger> manegers, Maneger maneger){
        manegers.add(maneger);
        System.out.println("success");
    }

    public void removeManeger(ArrayList<Maneger> manegers, Maneger maneger){
        for(Maneger mani : manegers){
            if(mani.name.equals(maneger.name))
                manegers.remove(mani);
        }
    }

    // 기능 2 사육사 등록 - 동물 할당
    public void ManegerMatchAnimal(ArrayList<Animal> animals, ArrayList<Maneger> manegers){
        int PerManger = animals.size()/manegers.size(); // 정수로 딱 떨어진다고 가정함
        int Mane_idx = 0;

        for(int i = 1; i<=animals.size(); i++){
            manegers.get(Mane_idx).required_Animals.add(animals.get(i-1));
            if(i%PerManger == 0 && i != animals.size()) {Mane_idx +=1;} // 사육사별로 지정된 수의 동물 저장
        }
    }

    // 기능 3 관리자별 동물 목록 조회
    public void viewManegerAnimals(ArrayList<Maneger> manegers){
        for(int i = 0; i<manegers.size(); i++){
            for(int j = 0; j< manegers.get(i).required_Animals.size(); j++)
                System.out.println(manegers.get(i).name + manegers.get(i).required_Animals.get(j));
        }
    }
}

