package com.wipro.bank.acc;

public class RDAccount extends Account {
    protected int tenure;
    protected float principle;

    public RDAccount(int tenure, float principle) {
        this.tenure = tenure;
        this.principle = principle;
    }

    public float calculateAmountDeposited() {
        return principle * tenure * 12;
    }

    public float calculateInterest() {
        int totalMonth = tenure * 12;
        float r = rateOfInterest / 100;
        int n = 4;
        float compoundInterest = 0.0f;

        for (int i = 1; i <= totalMonth; i++) {
            float t = (float) (totalMonth - i + 1) / 12.0f;
            compoundInterest += (float) (principle * (Math.pow(1 + r / n, n * t) - 1));
        }
        return compoundInterest;
    }
}
