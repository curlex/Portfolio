module Chatty
  def name
    self.class.name.split('Question').join
  end
end

class Question
  include Chatty
  attr_reader :x,:y,:operation
  def initialize
    generate_question
  end
  def generate_question
    @x = rand(@range)
    @y = rand(@range)
    operation = rand(2)
    case operation
      when 0
        @operation = @operation_one
      when 1
        @operation = @operation_two
    end
  end

  def to_s
    "What is #{@x} #{@operation} #{@y} ?"
  end
end