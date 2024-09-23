package springdata.technest22.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CalculateTest {

    @InjectMocks
    private Calculate calculate;
    @Test
    void sum(){
        int result = calculate.sum(10,5);

        assertThat(result).isEqualTo(15);

    }

    @Test
    void div(int a, int b){
        int result = calculate.div(20,5);
        assertThat(result).isEqualTo(4);
    }



}